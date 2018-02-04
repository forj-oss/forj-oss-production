
multibranchPipelineJob('go-config-layers') {
  description('Folder for Project go-config-layers generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('go-config-layers')
      }
  }

  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
