multibranchPipelineJob('forj-oss-infra') {
  description('Folder for Project forj-oss-infra generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          apiUri('')
          includes('*')
          repoOwner('forj-oss')
          repository('forj-oss-infra')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
