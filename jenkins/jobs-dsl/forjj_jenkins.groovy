
multibranchPipelineJob('forjj-jenkins') {
  description('Folder for Project forjj-jenkins generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('forjj-jenkins')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
